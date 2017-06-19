'''
Created on Jun 18, 2017

@author: dj
'''

from os import path

from ggplot import *


output_file_path = "../../../../Temp"
output_file = "SampleChart_GG.png"


print(diamonds.head())
plot = ggplot(diamonds, aes(x='carat', y='price', colour='cut')) +\
    geom_point(alpha=0.5) +\
    scale_color_gradient(low='#05D9F6', high='#5011D1') +\
    xlim(0, 6) + ylim(0, 20000) +\
    xlab("Carat") + ylab("Price") +\
    ggtitle("Diamond Price by Carat and Cut") +\
    theme_gray()
print(plot)

plot.save(path.join(output_file_path, output_file))

if __name__ == '__main__':
    pass
