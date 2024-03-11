from django.urls import path
from data import views


urlpatterns = [
   path('locations/',views.location_detail,name='location_details'),
   path('hotels/',views.get_hotel_names_by_city,name='hotel_based_on_location')
]