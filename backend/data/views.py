from rest_framework import status
from django.shortcuts import render
from django.http import JsonResponse
from .models import Location,Hotel,User_detail,Reservation
from rest_framework.decorators import api_view
from rest_framework.response import Response
from .serializer import LocationSerializer,HotelSerializer,HotelDataSerializer,UserSerializer,UserDataSerializer,ReservationListSerializer,ReservationDurationSerializer

@api_view(['GET'])
def location_detail(request):
    locations = Location.objects.all()
    serializer = LocationSerializer(locations)
    return Response(serializer.data)


@api_view(['POST'])
def get_hotel_names_by_city(request):
    serializer = HotelDataSerializer(data=request.data)
    if serializer.is_valid():
        city = serializer.validated_data['city']
        location = Location.objects.get(city=city)
        hotels = Hotel.objects.all().filter(hotel_location=location.location_id)
        hotel_list = list(hotels)
        serializer = HotelSerializer(hotels, many=True) 
        serialized_data = []
        for hotel in hotel_list:
            serialized_data.append({
                'hotel_id': hotel.hotel_id,
                'hotel_name': hotel.hotel_name,
                'hotel_location': hotel.hotel_location,
                'hotel_image':hotel.hotel_image
            })

        return Response(serialized_data)
    else:
        return Response(serializer.errors)
    


@api_view(['GET'])
def get_user_detail(request):
    users = User_detail.objects.all()
    serializer = UserSerializer(users)
    return Response(serializer.data)


@api_view(['POST'])
def get_user_details_basedon_gmail(request):
    serializer = UserDataSerializer(data=request.data)
    if serializer.is_valid():
        user_gmail = serializer.validated_data['user_gmail']
        user_details = User_detail.objects.get(user_gmail=user_gmail)
        serialized_data = UserSerializer(user_details)
        p = {
            'user_name': user_details.user_name,
            'user_gmail': user_details.user_gmail,
            'user_password': user_details.user_password
        }
        return Response(p)
    else:
        return Response(serializer.errors)
    

@api_view(['POST'])
def get_reservation_detail_list(request):
    Reservation_list = Reservation.objects.all()
    serializer = ReservationListSerializer(Reservation_list)
    return Response(serializer)


@api_view(['POST'])
def get_reservation_duration(request):
    serializer = ReservationDurationSerializer(data=request.data)
    if serializer.is_valid():
        rid = serializer.validated_data['reservation_id']
        print(rid)
        reservation = Reservation.objects.get(reservation_id=rid)
        p = {
            'reservation_id': reservation.reservation_id,
            'user_id': reservation.user_id.user_id,
            'hotel_id': reservation.hotel_id.hotel_id,
            'check_in': reservation.check_in,
            'check_out': reservation.check_out
        }
        print(p)

        return Response(p)
    else:
        return Response(serializer.errors)



    





# Create your views here.
