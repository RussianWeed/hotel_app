from rest_framework import serializers
from .models import Location,Hotel,User_detail

class LocationSerializer(serializers.Serializer):
    class Meta:
        model = Location
        fields = '__all__'

class HotelSerializer(serializers.ModelSerializer):
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
    

