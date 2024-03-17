from django.db import models


class Location(models.Model):
    location_id = models.IntegerField(primary_key=True)
    city = models.CharField(max_length=100)


class Hotel(models.Model):
    hotel_id = models.IntegerField(primary_key=True)
    hotel_name = models.CharField(max_length=100)
    hotel_image = models.CharField(max_length=200)
    hotel_location = models.ForeignKey(Location, on_delete=models.CASCADE)


class User_detail(models.Model):
    user_id = models.IntegerField(primary_key=True)
    user_name = models.CharField(max_length = 100)
    user_gmail =models.CharField(max_length=100)
    user_password = models.CharField(max_length=100)

class Reservation(models.Model):
    reservation_id = models.IntegerField(primary_key=True)
    user_id = models.ForeignKey(User_detail, on_delete=models.CASCADE)
    hotel_id = models.ForeignKey(Hotel, on_delete=models.CASCADE)
    check_in = models.DateTimeField()
    check_out = models.DateTimeField()

