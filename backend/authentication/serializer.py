from rest_framework import serializers


class AuthorizationSerializer(serializers.Serializer):
    username = serializers.CharField()
    gmail = serializers.EmailField()
    password1 = serializers.CharField()
    password2 = serializers.CharField()


class LoginSerializer(serializers.Serializer):
    username = serializers.CharField()
    password = serializers.CharField()
