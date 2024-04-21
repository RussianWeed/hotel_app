from django.db import connection
from django.shortcuts import render
from .models import Location,Hotel,User_detail,Reservation
from rest_framework.decorators import api_view
from rest_framework.response import Response
from .serializer import GetHotelListBasedOndateLocation,LocationSerializer,HotelSerializer,HotelDataSerializer,UserSerializer,UserDataSerializer,ReservationListSerializer,ReservationDurationSerializer

@api_view(['GET'])
def location_detail(request):
    locations = Location.objects.all()
    location_list=[]
    for location in locations:
        location_list.append({
            'location' : location.city,
            'location-id' : location.location_id,
        })
    return Response(location_list)

@api_view(['GET'])
def hotel_list(request):
    hotel_list = Hotel.objects.all()
    serialized_data = []
    for hotel in hotel_list:
        serialized_data.append({
            'hotel_name' : hotel.hotel_name,
            'hotel_location': hotel.hotel_location.city,
        })
        return Response(serialized_data)
    


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
        reservation = Reservation.objects.get(reservation_id=rid)
        p = {
            'reservation_id': reservation.reservation_id,
            'user_id': reservation.user_id.user_id,
            'hotel_id': reservation.hotel_id.hotel_id,
            'check_in': reservation.check_in,
            'check_out': reservation.check_out
        }
        return Response(p)
    else:
        return Response(serializer.errors)


@api_view(['POST'])
def get_reservation_details_basedon_gmail(request):
    serializer = UserDataSerializer(data=request.data)
    if serializer.is_valid():
        user_gmail = serializer.validated_data['user_gmail']
        print(user_gmail)
        user_details = User_detail.objects.get(user_gmail=user_gmail)
        User_id = user_details.user_id
        reservation_details = Reservation.objects.get(user_id=User_id)
        p = {
            'reservation_id':reservation_details.reservation_id,
            'user_id': reservation_details.user_id.user_id,
            'hotel_id': reservation_details.hotel_id.hotel_id,
            'check_in': reservation_details.check_in,
            'check_out': reservation_details.check_out
        }
        return Response(p)
    
    else:
        return Response(serializer.errors)
    


@api_view(['POST'])
def get_hotel_list_basedon_date_and_location(request):
    serialized_data = GetHotelListBasedOndateLocation(data=request.data)
    if serialized_data.is_valid():
        checkin = serialized_data.validated_data['checkin']
        checkout = serialized_data.validated_data['checkout']
        location = serialized_data.validated_data['location']
        location_id = Location.objects.get(city=location).location_id
        reserved_hotel_list = Reservation.objects.filter(check_in = checkin).filter(check_out = checkout)
        reserved_hotel_ids = reserved_hotel_list.values_list('hotel_id', flat=True).distinct()
        available_hotels = Hotel.objects.filter(hotel_location=location_id).exclude(hotel_id__in=reserved_hotel_ids)
        available_hotels_list = list(available_hotels)
        p=[]
        for hotel in available_hotels_list:
            p.append({
                "hotel-id" : hotel.hotel_id
            })
            print(connection.queries)
        return Response(p)
        
    else:
          return Response(serialized_data.errors)
        
        