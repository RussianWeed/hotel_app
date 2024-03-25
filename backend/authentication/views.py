from django.http import HttpResponse
from django.shortcuts import render
from rest_framework.decorators import api_view
from rest_framework.response import Response
from django.contrib.auth.models import User
from django.contrib import messages
from .serializer import AuthorizationSerializer

@api_view(['POST'])
def user_registration(request):
    
    serialized_data = AuthorizationSerializer(data=request.data)


    if serialized_data.is_valid():

        username = serialized_data.validated_data['username']
        gmail = serialized_data.validated_data['gmail']
        pass1 = serialized_data.validated_data['password1']
        pass2 = serialized_data.validated_data['password2']

        if pass1 == pass2:
                myuser = User.objects.create_user(username,gmail,pass1)
                myuser.save()
                messages.success(request, "Account created successfully!")
                return Response({"message": "Account created successfully!"})
        else:
                messages.error(request, "Passwords do not match!")
                return Response({"error": "Passwords do not match!"}, status=400)
        

    else:
          return Response(serialized_data.error_messages)