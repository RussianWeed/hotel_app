from rest_framework import serializers
from .models import Location
from .models import Hotel

class LocationSerializer(serializers.Serializer):
    class Meta:
        model = Location
        fields = '__all__'

class HotelSerializer(serializers.Serializer):
    class Meta:
        model = Hotel
        fields = '__all__'