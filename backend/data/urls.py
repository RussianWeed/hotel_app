from django.urls import path
from data import views


urlpatterns = [
   path('locations/',views.location_detail,name='location_details'),
   path('hotel-filter/',views.get_hotel_list_basedon_date_and_location,name="hotel list of a place based on location"),
   path('hotels/',views.get_hotel_names_by_city,name='hotel_based_on_location'), #jasonformat:{"city":"puri"}
   path('user-list/',views.get_user_detail,name='contact_detail'),
   path('user-detail/',views.get_user_details_basedon_gmail,name='get_user_details_basedon_email'), #{"user_gmail":"abdul@001gmail.com"}
   path('reservation-list/',views.get_reservation_detail_list,name='get_reservation_detail_list'),
   path('reservation-details/',views.get_reservation_duration,name='get_reservation_duration'),
   path('reservation-detail/',views.get_reservation_details_basedon_gmail,name='get_reservation_details_basedon_gmail')
]#{
# "checkin":"2008-11-11",
# "checkout":"2008-11-13",
# "location":"puri"
#}