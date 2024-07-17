import tkinter as tk
import time
import math
class AnalogWatch(tk.Tk):
    def __init__(self):
        super().__init__()
        self.title("Analog Watch")
        self.geometry("400x400")
        self.canvas = tk.Canvas(self, width=400, height=400, bg="white")
        self.canvas.pack()
        self.canvas.create_oval(50, 50, 350, 350)
        self.hour_hand = self.canvas.create_line(200, 200, 200, 150, width=6, fill="black")
        self.minute_hand = self.canvas.create_line(200, 200, 200, 100, width=4, fill="blue")
        self.second_hand = self.canvas.create_line(200, 200, 200, 50, width=2, fill="red")
        self.update_time()
    def update_time(self):
        current_time = time.localtime()
        hour = current_time.tm_hour
        minute = current_time.tm_min
        second = current_time.tm_sec
        hour_angle = math.radians((hour % 12) * 30 + minute / 2)
        self.rotate_hand(self.hour_hand, hour_angle, 50)
        minute_angle = math.radians(minute * 6 + second / 10)
        self.rotate_hand(self.minute_hand, minute_angle, 70)
        second_angle = math.radians(second * 6)
        self.rotate_hand(self.second_hand, second_angle, 90)
        self.after(1000, self.update_time)
    def rotate_hand(self, hand, angle, length):
        x, y = self.canvas.coords(hand)[:2]
        new_x = x + length * math.cos(angle)
        new_y = y + length * math.sin(angle)
        self.canvas.coords(hand, x, y, new_x, new_y)
if __name__ == "__main__":
    watch = AnalogWatch()
    watch.mainloop()
