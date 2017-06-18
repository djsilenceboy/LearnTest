'''
Created on Jun 18, 2017

@author: dj
'''

from os import path

import matplotlib.pyplot as plot


output_file_path = "../../../../Temp"

plot.style.use('ggplot')

students = ["Tom", "Jerry", "Mary", "John", "Jack"]
students_index = range(len(students))
scores = [60, 90, 95, 70, 80]

figure = plot.figure()
ax1 = figure.add_subplot(1, 1, 1)
ax1.bar(students_index, scores, align="center", color="blue")
ax1.xaxis.set_ticks_position("bottom")
ax1.yaxis.set_ticks_position("left")
plot.xticks(students_index, students, rotation=0, fontsize="small")

plot.xlabel('Student Name')
plot.ylabel('Score')
plot.title('Exam results 2017')

plot.savefig(path.join(output_file_path, "SamplePlot.png"),
             dpi=400, bbox_inches="tight")
plot.show()

if __name__ == '__main__':
    pass
