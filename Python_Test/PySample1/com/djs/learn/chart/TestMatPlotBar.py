'''
Created on Jun 18, 2017

@author: dj
'''

from os import path

from matplotlib import pyplot as plot


output_file_path = "../../../../Temp"
output_file = "SampleChart_Bar.png"

plot.style.use("ggplot")

students = ["Tom", "Jerry", "Mary", "John", "Jack"]
students_index = range(len(students))
scores = [60, 90, 95, 70, 80]

figure = plot.figure()
subplot = figure.add_subplot(1, 1, 1)

subplot.bar(students_index, scores, align="center", color="blue")

subplot.xaxis.set_ticks_position("bottom")
subplot.yaxis.set_ticks_position("left")
plot.xticks(students_index, students, rotation=0, fontsize="small")

plot.xlabel("Student Name")
plot.ylabel("Score")

# "plot.title" will be hidden by "subplot.set_title", if set.
plot.title("Bar sample 2")
figure.suptitle("Bar sample 1",
                fontsize=10, fontweight="bold")

plot.savefig(path.join(output_file_path, output_file),
             dpi=400, bbox_inches="tight")
plot.show()

if __name__ == '__main__':
    pass
