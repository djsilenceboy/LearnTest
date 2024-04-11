from enum import auto, Enum, Flag, unique

class MobilePhone1(Enum):
    APPLE = auto()
    SAMSUNG = auto()
    HUAWEI = auto()

class MobilePhone2(Enum):
    APPLE = "iPhone"
    SAMSUNG = "Galaxy"
    HUAWEI = "RongYao"

for i, mobilePhone1 in enumerate(MobilePhone1, start=1):
    print("MobilePhone1:", i, ":", mobilePhone1, "-", mobilePhone1.value);

for i, mobilePhone2 in enumerate(MobilePhone2, start=1):
    print("MobilePhone2:", i, ":", mobilePhone2, "-", mobilePhone2.value);


class Color(Flag):
    RED = auto()
    GREEN = auto()
    BLUE = auto()
    YELLOW = auto()

for i, color in enumerate(Color, start=1):
    print("Color:", i, ":", color, "-", color.value);

colorRG = Color.RED | Color.GREEN
print("colorRG =", colorRG)


class GameType1(Enum):
    RPG = "rpg"
    RPG2 = "rpg"
    AVG = "avg"
    AVG2 = "avg"

for i, gameType1 in enumerate(GameType1, start=1):
    print("GameType1:", i, ":", gameType1, "-", gameType1.value);

# GameType1: 1 : GameType1.RPG - rpg
# GameType1: 2 : GameType1.AVG - avg

@unique
class GameType2(Enum):
    RPG = "rpg"
    RPG2 = "rpg"
    AVG = "avg"
    AVG2 = "avg"

#   File "C:\Python312\Lib\enum.py", line 1597, in unique
#     raise ValueError('duplicate values found in %r: %s' %
# ValueError: duplicate values found in <enum 'GameType2'>: RPG2 -> RPG, AVG2 -> AVG
