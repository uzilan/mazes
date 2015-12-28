require 'recursive_back_tracker'
require 'grid'

grid = Grid.new(20, 20)
RecursiveBackTracker.on(grid)

filename = "images/recursive backtracker.png"
grid.to_png.save(filename)
puts "saved to #{filename}"