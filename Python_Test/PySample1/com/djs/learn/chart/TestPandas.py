'''
Created on Jun 18, 2017

@author: dj
'''

from os import path

from matplotlib import pyplot as plot
import numpy as np
import pandas as pd


output_file_path = "../../../../Temp"
output_file = "SampleChart_Pandas.png"


figure, axes = plot.subplots(nrows=1, ncols=2)
subplot1, subplot2 = axes.ravel()

data_frame = pd.DataFrame(np.random.rand(5, 3),
                          index=["Customer 1", "Customer 2",
                                 "Customer 3", "Customer 4", "Customer 5"],
                          columns=pd.Index(["Metric 1", "Metric 2", "Metric 3"], name="Metrics"))

data_frame.plot(kind="bar", ax=subplot1, alpha=0.75, title="Bar Plot")
plot.setp(subplot1.get_xticklabels(), rotation=45, fontsize=10)
plot.setp(subplot1.get_yticklabels(), rotation=0, fontsize=10)
subplot1.set_xlabel("Customer")
subplot1.set_ylabel("Value")
subplot1.xaxis.set_ticks_position("bottom")
subplot1.yaxis.set_ticks_position("left")

colors = dict(boxes="DarkBlue", whiskers="Gray", medians="Red", caps="Black")
data_frame.plot(kind="box", color=colors, sym="r.",
                ax=subplot2, title="Box Plot")
plot.setp(subplot2.get_xticklabels(), rotation=45, fontsize=10)
plot.setp(subplot2.get_yticklabels(), rotation=0, fontsize=10)
subplot2.set_xlabel("Metric")
subplot2.set_ylabel("Value")
subplot2.xaxis.set_ticks_position("bottom")
subplot2.yaxis.set_ticks_position("left")

plot.savefig(path.join(output_file_path, output_file),
             dpi=400, bbox_inches="tight")
plot.show()

if __name__ == '__main__':
    pass
