# Generated by Django 5.0.2 on 2024-03-17 14:48

import django.db.models.deletion
from django.db import migrations, models


class Migration(migrations.Migration):

    initial = True

    dependencies = [
    ]

    operations = [
        migrations.CreateModel(
            name='Location',
            fields=[
                ('location_id', models.IntegerField(primary_key=True, serialize=False)),
                ('city', models.CharField(max_length=100)),
            ],
        ),
        migrations.CreateModel(
            name='User_detail',
            fields=[
                ('user_id', models.IntegerField(primary_key=True, serialize=False)),
                ('user_name', models.CharField(max_length=100)),
                ('user_gmail', models.CharField(max_length=100)),
                ('user_password', models.CharField(max_length=100)),
            ],
        ),
        migrations.CreateModel(
            name='Hotel',
            fields=[
                ('hotel_id', models.IntegerField(primary_key=True, serialize=False)),
                ('hotel_name', models.CharField(max_length=100)),
                ('hotel_image', models.CharField(max_length=200)),
                ('hotel_location', models.ForeignKey(on_delete=django.db.models.deletion.CASCADE, to='data.location')),
            ],
        ),
        migrations.CreateModel(
            name='Reservation',
            fields=[
                ('reservation_id', models.IntegerField(primary_key=True, serialize=False)),
                ('check_in', models.DateTimeField()),
                ('check_out', models.DateTimeField()),
                ('hotel_id', models.ForeignKey(on_delete=django.db.models.deletion.CASCADE, to='data.hotel')),
                ('user_id', models.ForeignKey(on_delete=django.db.models.deletion.CASCADE, to='data.user_detail')),
            ],
        ),
    ]
