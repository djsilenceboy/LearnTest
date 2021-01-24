
package com.djs.fun;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

/*
 * ================================================================================
 * Position [1, 25, 50, 75, 100] = [[0:100, 24:100, 49:100, 74:100, 99:100]]
 * ------------------------------------------------------------
 * Position [1, 25, 50, 75, 100] = [[3:-141, 95:38, 63:102, 32:159, 78:331]]
 * Top Rich = [[17:201, 40:203, 76:207, 84:213, 57:233, 21:241, 79:244, 38:247, 0:266, 41:272, 55:273, 90:275, 64:289, 99:304, 78:331]]
 * ================================================================================
 * Position [1, 25, 50, 75, 100] = [[0:500, 24:100, 49:100, 74:100, 99:100]]
 * Rich = [[0:500, 1:500, 2:500, 3:500, 4:500, 5:500, 6:500, 7:500, 8:500, 9:500]]
 * ------------------------------------------------------------
 * Position [1, 25, 50, 75, 100] = [[48:-134, 53:38, 64:99, 41:199, 1:681]]
 * Top Rich = [[27:277, 87:278, 11:301, 56:305, 13:312, 70:315, 7:395, 0:405, 6:470, 5:481, 9:483, 4:563, 8:620, 2:636, 1:681]]
 * ------------------------------------------------------------
 * Rich = [[0:405, 1:681, 2:636, 3:264, 4:563, 5:481, 6:470, 7:395, 8:620, 9:483]]
 * ================================================================================
 * Position [1, 25, 50, 75, 100] = [[0:500, 24:100, 49:100, 74:100, 99:100]]
 * Rich = [[0:500, 1:500, 2:500, 3:500, 4:500, 5:500, 6:500, 7:500, 8:500, 9:500]]
 * Work Hard = [[10:100, 11:100, 12:100, 13:100, 14:100, 15:100, 16:100, 17:100, 18:100, 19:100]]
 * ------------------------------------------------------------
 * Position [1, 25, 50, 75, 100] = [[70:-161, 41:28, 85:115, 49:222, 3:594]]
 * Top Rich = [[44:297, 0:308, 19:311, 96:331, 1:334, 69:339, 11:362, 15:362, 9:380, 87:410, 6:428, 4:456, 8:482, 5:484, 3:594]]
 * ------------------------------------------------------------
 * Rich = [[0:308, 1:334, 2:224, 3:594, 4:456, 5:484, 6:428, 7:273, 8:482, 9:380]]
 * Work Hard = [[10:257, 11:362, 12:110, 13:167, 14:206, 15:362, 16:228, 17:166, 18:264, 19:311]]
 * ================================================================================
 */
public class RandomDistribution
{
	public static int MAX_ENTITY_QUANTITY = 100;
	public static int MAX_ENTITY_QUANTITY_RICH = 10;
	public static int MAX_ENTITY_QUANTITY_WORK_HARD = 10;
	public static int MAX_ENTITY_AMOUNT_DEFAULT = 100;
	public static int MAX_ENTITY_AMOUNT_RICH = 500;
	public static int AMOUNT_REDISTRIBUTED_PER_ROUND_DEFAULT = 1;
	public static int AMOUNT_REDISTRIBUTED_PER_ROUND_RICH = 2;
	public static int CHANGE_PER_DEFAULT = 100;
	public static int CHANGE_PER_RICH = 200;
	public static int CHANGE_PER_WORK_HARD = 102;
	public static int MAX_DISTRIBUTION_ROUNDS = 10000;

	private Random randomId = new Random();

	public String getEntityInfo(Entity entity){
		String results = String.format("%d:%d", entity.getId(), entity.getCurrentAmount().get());
		return results;
	}

	public List<String> getEntitiesInfo(Entity[] entities, List<Integer> ids){
		List<String> results = new ArrayList<>();
		ids.stream().forEach(id -> results.add(getEntityInfo(entities[id])));
		return results;
	}

	public List<String> getEntitiesInfo(List<Entity> entities, List<Integer> ids){
		List<String> results = new ArrayList<>();
		ids.stream().forEach(id -> results.add(getEntityInfo(entities.get(id))));
		return results;
	}

	public void printEntities(Entity[] entities){
		String line = String.format("Position [1, 25, 50, 75, 100] = [%s]",
		                            getEntitiesInfo(entities, Arrays.asList(0, 24, 49, 74, 99)));
		System.out.println(line);
	}

	public void printEntitiesRich(Entity[] entities, int richNumber){
		List<Integer> richIds = new ArrayList<>();
		IntStream.range(0, richNumber).forEach(id -> {
			richIds.add(id);
		});

		String line = String.format("Rich = [%s]", getEntitiesInfo(entities, richIds));
		System.out.println(line);
	}

	public void printEntitiesWorkHard(Entity[] entities, int richNumber, int workHardNumber){
		List<Integer> workHardIds = new ArrayList<>();
		IntStream.range(richNumber, richNumber + workHardNumber).forEach(id -> {
			workHardIds.add(id);
		});

		String line = String.format("Work Hard = [%s]", getEntitiesInfo(entities, workHardIds));
		System.out.println(line);
	}

	public void printEntitiesByAmount(int round, Entity[] entities){
		List<Entity> entityList = new ArrayList<>(Arrays.asList(entities));
		entityList.sort(Comparator.comparing(e -> e.getCurrentAmount().get()));
		String line = String.format("Position [1, 25, 50, 75, 100] = [%s]",
		                            getEntitiesInfo(entityList, Arrays.asList(0, 24, 49, 74, 99)));
		System.out.println(line);

		List<Integer> topRichIds = new ArrayList<>();
		IntStream.range(85, MAX_ENTITY_QUANTITY).forEach(id -> {
			topRichIds.add(id);
		});

		line = String.format("Top Rich = [%s]", getEntitiesInfo(entityList, topRichIds));
		System.out.println(line);

	}

	public void redistributeOneRoundA(Entity[] entities){
		List<Entity> entityList = Arrays.asList(entities);
		AtomicInteger collectedSum = new AtomicInteger(0);

		entityList.stream().parallel().forEach(e -> {
			e.getCurrentAmount().addAndGet(-e.getContributionAmount());
			collectedSum.addAndGet(e.getContributionAmount());
		});

		IntStream.range(0, collectedSum.get()).parallel().forEach(i -> {
			int id = randomId.nextInt(MAX_ENTITY_QUANTITY);
			entities[id].getCurrentAmount().addAndGet(1);
		});
	}

	public void redistributeOneRoundB(Entity[] entities, int richNumber){
		List<Entity> entityList = Arrays.asList(entities);
		AtomicInteger collectedSum = new AtomicInteger(0);

		entityList.stream().parallel().forEach(e -> {
			e.getCurrentAmount().addAndGet(-e.getContributionAmount());
			collectedSum.addAndGet(e.getContributionAmount());
		});

		IntStream.range(0, collectedSum.get()).parallel().forEach(i -> {
			int id = randomId.nextInt(MAX_ENTITY_QUANTITY + richNumber) % MAX_ENTITY_QUANTITY;
			entities[id].getCurrentAmount().addAndGet(1);
		});
	}

	public void redistributeOneRoundC(Entity[] entities, int richNumber, int workHardNumber){
		List<Entity> entityList = Arrays.asList(entities);
		AtomicInteger collectedSum = new AtomicInteger(0);

		entityList.stream().parallel().forEach(e -> {
			e.getCurrentAmount().addAndGet(-e.getContributionAmount());
			collectedSum.addAndGet(e.getContributionAmount());
		});

		int richChance = richNumber * CHANGE_PER_RICH;
		int workHardChance = workHardNumber * CHANGE_PER_WORK_HARD;
		int otheChance = (MAX_ENTITY_QUANTITY - richNumber - workHardNumber) * CHANGE_PER_DEFAULT;
		int maxChance = richChance + workHardChance + otheChance;
		// System.out.println(String.format("richChance=%d, workHardChance=%d, otheChance=%d, maxChance=%d", richChance, workHardChance, otheChance, maxChance));

		IntStream.range(0, collectedSum.get()).parallel().forEach(i -> {
			int chance = randomId.nextInt(maxChance);
			int id = -1;
			if (chance < richChance) {
				id = chance / CHANGE_PER_RICH;
			} else if (chance < (richChance + workHardChance)) {
				id = richNumber + (chance - richChance) / CHANGE_PER_WORK_HARD;
			} else {
				id = richNumber + workHardNumber + (chance - richChance - workHardChance) / CHANGE_PER_DEFAULT;
			}
			// System.out.println(String.format("chance=%d, id=%d", chance, id));
			entities[id].getCurrentAmount().addAndGet(1);
		});
	}

	public void redistributeMultipleRoundsA(){
		Entity[] entities = new Entity[MAX_ENTITY_QUANTITY];
		IntStream.range(0, MAX_ENTITY_QUANTITY).forEach(id -> {
			entities[id] = new Entity();
			entities[id].setId(id);
			entities[id].setOrginialAmount(MAX_ENTITY_AMOUNT_DEFAULT);
			entities[id].getCurrentAmount().addAndGet(MAX_ENTITY_AMOUNT_DEFAULT);
			entities[id].setContributionAmount(AMOUNT_REDISTRIBUTED_PER_ROUND_DEFAULT);
		});
		printEntities(entities);
		System.out.println("------------------------------------------------------------");

		IntStream.range(0, MAX_DISTRIBUTION_ROUNDS).forEach(i -> {
			redistributeOneRoundA(entities);
		});

		printEntitiesByAmount(MAX_DISTRIBUTION_ROUNDS - 1, entities);
	}

	public void redistributeMultipleRoundsB(){
		Entity[] entities = new Entity[MAX_ENTITY_QUANTITY];
		IntStream.range(0, MAX_ENTITY_QUANTITY).forEach(id -> {
			entities[id] = new Entity();
			entities[id].setId(id);
			if (id >= MAX_ENTITY_QUANTITY_RICH) {
				entities[id].setOrginialAmount(MAX_ENTITY_AMOUNT_DEFAULT);
				entities[id].getCurrentAmount().addAndGet(MAX_ENTITY_AMOUNT_DEFAULT);
				entities[id].setContributionAmount(AMOUNT_REDISTRIBUTED_PER_ROUND_DEFAULT);
			} else {
				entities[id].setOrginialAmount(MAX_ENTITY_AMOUNT_RICH);
				entities[id].getCurrentAmount().addAndGet(MAX_ENTITY_AMOUNT_RICH);
				entities[id].setContributionAmount(AMOUNT_REDISTRIBUTED_PER_ROUND_RICH);
			}
		});
		printEntities(entities);
		printEntitiesRich(entities, MAX_ENTITY_QUANTITY_RICH);
		System.out.println("------------------------------------------------------------");

		IntStream.range(0, MAX_DISTRIBUTION_ROUNDS).forEach(i -> {
		    // redistributeOneRoundB(entities, MAX_ENTITY_QUANTITY_RICH);
		    redistributeOneRoundC(entities, MAX_ENTITY_QUANTITY_RICH, 0);
		});

		printEntitiesByAmount(MAX_DISTRIBUTION_ROUNDS - 1, entities);
		System.out.println("------------------------------------------------------------");

		printEntitiesRich(entities, MAX_ENTITY_QUANTITY_RICH);
	}

	public void redistributeMultipleRoundsC(){
		Entity[] entities = new Entity[MAX_ENTITY_QUANTITY];
		IntStream.range(0, MAX_ENTITY_QUANTITY).forEach(id -> {
			entities[id] = new Entity();
			entities[id].setId(id);
			if (id >= MAX_ENTITY_QUANTITY_RICH) {
				entities[id].setOrginialAmount(MAX_ENTITY_AMOUNT_DEFAULT);
				entities[id].getCurrentAmount().addAndGet(MAX_ENTITY_AMOUNT_DEFAULT);
				entities[id].setContributionAmount(AMOUNT_REDISTRIBUTED_PER_ROUND_DEFAULT);
			} else {
				entities[id].setOrginialAmount(MAX_ENTITY_AMOUNT_RICH);
				entities[id].getCurrentAmount().addAndGet(MAX_ENTITY_AMOUNT_RICH);
				entities[id].setContributionAmount(AMOUNT_REDISTRIBUTED_PER_ROUND_RICH);
			}
		});
		printEntities(entities);
		printEntitiesRich(entities, MAX_ENTITY_QUANTITY_RICH);
		printEntitiesWorkHard(entities, MAX_ENTITY_QUANTITY_RICH, MAX_ENTITY_QUANTITY_WORK_HARD);
		System.out.println("------------------------------------------------------------");

		IntStream.range(0, MAX_DISTRIBUTION_ROUNDS).forEach(i -> {
			redistributeOneRoundC(entities, MAX_ENTITY_QUANTITY_RICH, MAX_ENTITY_QUANTITY_WORK_HARD);
			// printEntitiesByAmountWithId(i, entities);
		});

		printEntitiesByAmount(MAX_DISTRIBUTION_ROUNDS - 1, entities);
		System.out.println("------------------------------------------------------------");

		printEntitiesRich(entities, MAX_ENTITY_QUANTITY_RICH);
		printEntitiesWorkHard(entities, MAX_ENTITY_QUANTITY_RICH, MAX_ENTITY_QUANTITY_WORK_HARD);
	}

	public static void main(String[] args){
		RandomDistribution test = new RandomDistribution();
		System.out.println("================================================================================");

		test.redistributeMultipleRoundsA();
		System.out.println("================================================================================");

		test.redistributeMultipleRoundsB();
		System.out.println("================================================================================");

		test.redistributeMultipleRoundsC();
		System.out.println("================================================================================");
	}
}

class Entity
{
	private int id;
	private int orginialAmount;
	private AtomicInteger currentAmount = new AtomicInteger();
	private int contributionAmount;

	public int getId(){
		return id;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getOrginialAmount(){
		return orginialAmount;
	}

	public void setOrginialAmount(int orginialAmount){
		this.orginialAmount = orginialAmount;
	}

	public AtomicInteger getCurrentAmount(){
		return currentAmount;
	}

	public void setCurrentAmount(AtomicInteger currentAmount){
		this.currentAmount = currentAmount;
	}

	public int getContributionAmount(){
		return contributionAmount;
	}

	public void setContributionAmount(int contributionAmount){
		this.contributionAmount = contributionAmount;
	}
}
