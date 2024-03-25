from django.urls import path
from authentication import views

urlpatterns=[
path('signup/',views.user_registration,name="user-registration"),
# {"username":"ami",
# "gmail":"ami@gmail.com",
# "pass1":"nopass",
# "pass2":"nopass"}

path("login/",views.user_login,name="user-login"),

]