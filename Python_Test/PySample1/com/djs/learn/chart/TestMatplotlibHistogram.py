'''
Created on Jun 18, 2017

@author: dj
'''

from os import path

from matplotlib import pyplot as plot

import numpy as np


output_file_path = "../../../../Temp"
output_file = "SampleChart_Histogram.png"

plot.style.use("ggplot")

mu1, mu2, sigma = 100, 130, 15
x1 = mu1 + sigma * np.random.randn(10000)
x2 = mu2 + sigma * np.random.randn(10000)

figure = plot.figure()
subplot = figure.add_subplot(1, 1, 1)

n, bins, patches = subplot.hist(x1, bins=50, color="darkgreen")
n, bins, patches = subplot.hist(x2, bins=50, color="orange", alpha=0.5)

subplot.xaxis.set_ticks_position("bottom")
subplot.yaxis.set_ticks_position("left")
plot.xlabel("Bins")
plot.ylabel("Number")

# "plot.title" will be hidden by "subplot.set_title".
plot.title("Histogram sample 3")
figure.suptitle("Histogram sample 1", fontsize=14, fontweight="bold")
subplot.set_title("Histogram sample 2")

plot.savefig(path.join(output_file_path, output_file),
             dpi=400, bbox_inches="tight")
plot.show()

if __name__ == '__main__':
    pass
