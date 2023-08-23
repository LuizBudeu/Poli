from .common.settings import *
from .common.ui_utils import *


class Asteroid:
    def __init__(self, column_pos, total_columns, y=70, vely=1):
        self.image = pygame.image.load("assets/images/asteroid.png").convert_alpha()
        self.rect = self.image.get_rect()
        self.angle = 0
        self.vely = vely
        self.column_pos = column_pos
        self.posses = {i: int(WINDOW_SIZE[0]//total_columns * (i-0.5)) for i in range(1, total_columns+1)}
        self.rect.center = (self.posses[column_pos], y)
        
    def draw(self, screen):
        screen.blit(self.image_copy, (self.rect.centerx-self.image_copy.get_width()//2, self.rect.centery-self.image_copy.get_height()//2))
        
    def update(self):
        self.rect.center = (self.posses[self.column_pos], self.rect.center[1] + self.vely) 
        self.angle += 1
        self.image_copy = pygame.transform.rotate(self.image, self.angle)

    def move(self, dist):
        self.rect.center = (self.posses[self.column_pos], dist) 

        