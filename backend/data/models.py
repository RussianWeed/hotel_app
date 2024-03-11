from django.db import models


class Location(models.Model):
    location_id = models.IntegerField(primary_key = True)
    city = models.CharField(max_length=100)

class Hotel(models.Model):
    hotel_id = models.IntegerField(primary_key=True)
    hotel_name = models.CharField(max_length=100)
    hotel_location = models.ForeignKey(Location,null=False,on_delete=models.CASCADE)



# Create your models here.
