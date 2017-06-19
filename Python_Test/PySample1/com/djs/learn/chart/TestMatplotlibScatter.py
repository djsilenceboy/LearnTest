'''
Created on Jun 18, 2017

@author: dj
'''

from os import path

from matplotlib import pyplot as plot
from numpy.random import randn

import numpy as np


output_file_path = "../../../../Temp"
output_file = "SampleChart_Scatter.png"

plot.style.use("ggplot")

x = np.arange(start=1., stop=15., step=1.)

# Separated dots.
y_linear = x + 5. * randn(14)
y_quadratic = x**2 + 10. * randn(14)

# Continuous lines.
fn_linear = np.poly1d(np.polyfit(x, y_linear, deg=1))
fn_quadratic = np.poly1d(np.polyfit(x, y_quadratic, deg=2))

figure = plot.figure()
subplot = figure.add_subplot(1, 1, 1)

subplot.plot(x, y_linear, "bo", x, y_quadratic, "go",
             x, fn_linear(x), "b-", x, fn_quadratic(x), "g-", linewidth=2.)

subplot.xaxis.set_ticks_position("bottom")
subplot.yaxis.set_ticks_position("left")

plot.xlabel("x")
plot.ylabel("f(x)")
plot.xlim((min(x) - 1., max(x) + 1.))
plot.ylim((min(y_quadratic) - 10., max(y_quadratic) + 10.))

# "plot.title" will be hidden by "subplot.set_title", if set.
plot.title("Scatter sample 2")
figure.suptitle("Scatter sample 1",
                fontsize=10, fontweight="bold")

plot.savefig(path.join(output_file_path, output_file),
             dpi=400, bbox_inches="tight")
plot.show()

if __name__ == '__main__':
    pass
