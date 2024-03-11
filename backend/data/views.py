from django.shortcuts import render
from django.http import JsonResponse
from .models import Location,Hotel
from rest_framework.decorators import api_view
from rest_framework.response import Response
from .serializer import LocationSerializer,HotelSerializer


@api_view(['GET'])
def location_detail(request):
    locations = Location.objects.all()
    serializer = LocationSerializer(locations)
    return Response(serializer.data)


@api_view(['GET'])
def get_hotel_names_by_city(request, city_name):
   hotel_names = Hotel.objects.all(city_name=Hotel.hotel_location)
   serializer = HotelSerializer(hotel_names)
   return Response(serializer.data)

# Create your views here.
