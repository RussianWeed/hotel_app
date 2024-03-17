from rest_framework import serializers
from .models import Location,Hotel,User_detail,Reservation

class LocationSerializer(serializers.Serializer):
    class Meta:
        model = Location
        fields = '__all__'

class HotelSerializer(serializers.Serializer):
    class Meta:
        model = Hotel
        fields = '__all__'

class HotelDataSerializer(serializers.Serializer):
    city = serializers.CharField()
    class Meta:
        model = Location
        fields = ('city')

class UserSerializer(serializers.Serializer):
    class Meta:
        fields= '__all__'

class UserDataSerializer(serializers.Serializer):
    user_gmail = serializers.CharField()
    class Meta:
        model = User_detail
        fields=('user_gmail')

class ReservationListSerializer(serializers.Serializer):
    class Meta:
        model = Reservation
        fields = '__all__'

class ReservationDurationSerializer(serializers.Serializer):
    # city = serializers.CharField()
    reservation_id = serializers.IntegerField()
    class Meta:
        model = Reservation  
        fields = ('reservation_id')  

