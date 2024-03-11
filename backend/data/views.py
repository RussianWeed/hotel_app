from django.shortcuts import render
from django.http import JsonResponse
from .models import Location,Hotel
from rest_framework.decorators import api_view
from rest_framework.response import Response
from .serializer import LocationSerializer,HotelSerializer, Serializer

@api_view(['GET'])
def location_detail(request):
    locations = Location.objects.all()
    serializer = LocationSerializer(locations)
    return Response(serializer.data)

@api_view(['POST'])
def get_hotel_names_by_city(request):
    serializer = Serializer(data=request.data)
    print(serializer)
    if serializer.is_valid():
        city = serializer.validated_data['city']
        print(city)
        location = Location.objects.get(city=city)
        print(location.location_id)
        # hotel = Hotel.objects.get(hotel_location=location.location_id)
        print(Hotel.objects.all())
        hotels = Hotel.objects.all().filter(hotel_location=location.location_id)
        hotel_list = list(hotels)
        print(hotel_list)
        serializer = HotelSerializer(hotels, many=True)  # Use data for multiple objects
        print(serializer)
        serialized_data = []
        for hotel in hotel_list:
            serialized_data.append({
                'hotel_id': hotel.hotel_id,
                'hotel_name': hotel.hotel_name,
                'hotel_location': hotel.hotel_location_id
            })
        print(serialized_data)
        return Response(serialized_data)
    else:
        return Response(serializer.errors)