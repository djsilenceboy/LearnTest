"""
Created on Jun 18, 2017

@author: dj
"""

from matplotlib import pyplot as plot
import numpy as np
import pandas as pd
import seaborn as sns

sns.set(color_codes=True)


# Scatter plot
mean, cov = [5, 10], [(1, .5), (.5, 1)]
data = np.random.multivariate_normal(mean, cov, 200)
data_frame = pd.DataFrame(data, columns=["x", "y"])
sns.jointplot(x="x", y="y", data=data_frame,
              kind="reg").set_axis_labels("x", "y")
plot.suptitle(
    "Joint Plot of Two Variables with Bivariate and Univariate Graphs")
plot.show()


# Pairwise bivariate
#iris = sns.load_dataset("iris")
# sns.pairplot(iris)
# plot.show()


# Linear regression model
tips = sns.load_dataset("tips")
#sns.lmplot(x="total_bill", y="tip", data=tips)
sns.lmplot(x="size", y="tip", data=tips, x_jitter=.15, ci=None)
#sns.lmplot(x="size", y="tip", data=tips, x_estimator=np.mean, ci=None)
plot.show()


# Box plots
sns.boxplot(x="day", y="total_bill", hue="time", data=tips)
sns.factorplot(x="time", y="total_bill", hue="smoker",
               col="day", data=tips, kind="box", size=4, aspect=.5)
plot.show()


# Bar plots
titanic = sns.load_dataset("titanic")
sns.barplot(x="sex", y="survived", hue="class", data=titanic)
plot.show()
sns.countplot(y="deck", hue="class", data=titanic, palette="Greens_d")
plot.show()

# Non-linear regression model
anscombe = sns.load_dataset("anscombe")
# polynomial
sns.lmplot(x="x", y="y", data=anscombe.query("dataset == 'II'"),
           order=2, ci=False, scatter_kws={"s": 80})
plot.show()


# robust to outliers
# sns.lmplot(x="x", y="y", data=anscombe.query("dataset == 'III'"),
#           robust=True, ci=False, scatter_kws={"s": 80})
# plot.show()


# logistic
tips["big_tip"] = (tips.tip / tips.total_bill) > .15
sns.lmplot(x="total_bill", y="big_tip", data=tips, logistic=True,
           y_jitter=.03).set_axis_labels("Total Bill", "Big Tip")
plot.title("Logistic Regression of Big Tip vs. Total Bill")
plot.show()


# lowess smoother
sns.lmplot(x="total_bill", y="tip", data=tips, lowess=True)
plot.show()


# Condition on other variables
sns.lmplot(x="total_bill", y="tip", hue="smoker", data=tips,
           markers=["o", "x"], palette="Set1")
# sns.lmplot(x="total_bill", y="tip", hue="smoker",
#           col="time", row="sex", data=tips)
plot.show()


# Control shape and size of plot
sns.lmplot(x="total_bill", y="tip", col="day", data=tips, col_wrap=2, size=3)
#sns.lmplot(x="total_bill", y="tip", col="day", data=tips, aspect=.5)
plot.show()


# Plotting regression in other contexts
sns.jointplot(x="total_bill", y="tip", data=tips, kind="reg")
sns.pairplot(tips, x_vars=["total_bill", "size"], y_vars=["tip"],
             size=5, aspect=.8, kind="reg")
# sns.pairplot(tips, x_vars=["total_bill", "size"], y_vars=["tip"],
#             hue="smoker", size=5, aspect=.8, kind="reg")
plot.show()

if __name__ == '__main__':
    pass
