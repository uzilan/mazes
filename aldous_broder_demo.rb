require 'grid'
require 'aldous_broder'

grid = Grid.new(20, 20)
AldousBroder.on(grid)

filname = "aldous_broder.png"
grid.to_png.save(filname)
puts "saved #{filname}"