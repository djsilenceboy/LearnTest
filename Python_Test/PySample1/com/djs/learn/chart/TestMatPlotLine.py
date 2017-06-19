'''
Created on Jun 18, 2017

@author: dj
'''

from os import path
from matplotlib import pyplot as plot
from numpy.random import randn

output_file_path = "../../../../Temp"
output_file = "SampleChart_Line.png"

plot.style.use("ggplot")

plot_data1 = randn(50).cumsum()
plot_data2 = randn(50).cumsum()
plot_data3 = randn(50).cumsum()
plot_data4 = randn(50).cumsum()

figure = plot.figure()
subplot = figure.add_subplot(1, 1, 1)

subplot.plot(plot_data1, marker=r'o', color=u'blue',
             linestyle="-", label="Blue Solid")
subplot.plot(plot_data2, marker=r'+', color=u'red',
             linestyle="--", label="Red Dashed")
subplot.plot(plot_data3, marker=r'*', color=u'green',
             linestyle="-.", label="Green Dash Dot")
subplot.plot(plot_data4, marker=r's', color=u'orange',
             linestyle=":", label="Orange Dotted")

subplot.xaxis.set_ticks_position("bottom")
subplot.yaxis.set_ticks_position("left")

plot.xlabel("Draw")
plot.ylabel("Random Number")

# "plot.title" will be hidden by "subplot.set_title", if set.
plot.title("Line sample 2")
figure.suptitle("Line sample 1",
                fontsize=10, fontweight="bold")

plot.savefig(path.join(output_file_path, output_file),
             dpi=400, bbox_inches="tight")
plot.show()

if __name__ == '__main__':
    pass
