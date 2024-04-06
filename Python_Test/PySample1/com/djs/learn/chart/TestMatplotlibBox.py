'''
Created on Jun 18, 2017

@author: dj
'''

from os import path

from matplotlib import pyplot as plot
import numpy as np


output_file_path = "../../../../Temp"
output_file = "SampleChart_Box.png"

plot.style.use("ggplot")

N = 500
normal = np.random.normal(loc=0.0, scale=1.0, size=N)
lognormal = np.random.lognormal(mean=0.0, sigma=1.0, size=N)
index_value = np.random.randint(low=0, high=N - 1, size=N)
normal_sample = normal[index_value]
lognormal_sample = lognormal[index_value]
box_plot_data = [normal, normal_sample, lognormal, lognormal_sample]

figure = plot.figure()
subplot = figure.add_subplot(1, 1, 1)

box_labels = ["normal", "normal_sample", "lognormal", "lognormal_sample"]
subplot.boxplot(box_plot_data, notch=False, sym=".", vert=True, whis=1.5,
                showmeans=True, labels=box_labels)

subplot.xaxis.set_ticks_position("bottom")
subplot.yaxis.set_ticks_position("left")

plot.xlabel("Distribution")
plot.ylabel("Value")

# "plot.title" will be hidden by "subplot.set_title", if set.
plot.title("Box sample 2")
figure.suptitle("Box sample 1",
                fontsize=10, fontweight="bold")

plot.savefig(path.join(output_file_path, output_file),
             dpi=400, bbox_inches="tight")
plot.show()

if __name__ == '__main__':
    pass
