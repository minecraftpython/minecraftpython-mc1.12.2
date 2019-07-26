import math

class MinecraftPythonVec3:
	def __init__(self, x, y, z):
		self.x = x
		self.y = y
		self.z = z

	def __add__(self, other):
		return MinecraftPythonVec3(other.x + self.x, other.y + self.y, other.z + self.z)

	def __sub__(self, other):
		return MinecraftPythonVec3(-other.x + self.x, -other.y + self.y, -other.z + self.z)

	def __mul__(self, other):
		return MinecraftPythonVec3(self.x * other, self.y * other, self.z * other)

	def cross(self, otherVector):
		newX = self.y * otherVector.z - self.z * otherVector.y
		newY = self.x * otherVector.z - self.z * otherVector.x
		newZ = self.x * otherVector.y - self.y * otherVector.x
		return MinecraftPythonVec3(newX, newY, newZ)

	def normalize(self):
		length = math.sqrt(self.x * self.x + self.y * self.y + self.z * self.z)
		return MinecraftPythonVec3(self.x / length, self.y / length, self.z / length)

	def stringify(self):
		return "X: " + str(self.x) + " | Y: " + str(self.y) + " | Z: " + str(self.z)
