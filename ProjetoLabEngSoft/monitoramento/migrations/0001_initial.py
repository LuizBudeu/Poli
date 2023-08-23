# Generated by Django 4.1.1 on 2022-10-07 18:48

from django.db import migrations, models


class Migration(migrations.Migration):

    initial = True

    dependencies = [
    ]

    operations = [
        migrations.CreateModel(
            name='Chegadas',
            fields=[
                ('id', models.BigAutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('origem', models.CharField(max_length=100)),
                ('chegada_prevista', models.DateTimeField()),
                ('chegada_real', models.DateTimeField()),
            ],
            options={
                'db_table': 'chegadas',
            },
        ),
        migrations.CreateModel(
            name='Partidas',
            fields=[
                ('id', models.BigAutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('status', models.CharField(choices=[('embarcando', 'embarcando'), ('cancelado', 'cancelado'), ('programado', 'programado'), ('taxiando', 'taxiando'), ('pronto', 'pronto'), ('autorizado', 'autorizado'), ('em voo', 'em voo'), ('aterrissado', 'aterrissado')], max_length=20)),
                ('destino', models.CharField(max_length=100)),
                ('partida_prevista', models.DateTimeField()),
                ('partida_real', models.DateTimeField()),
            ],
            options={
                'db_table': 'partidas',
            },
        ),
        migrations.CreateModel(
            name='Voos',
            fields=[
                ('id', models.BigAutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('companhia_aerea', models.CharField(max_length=100)),
                ('codigo', models.CharField(max_length=6)),
                ('status', models.CharField(choices=[('embarcando', 'embarcando'), ('cancelado', 'cancelado'), ('programado', 'programado'), ('taxiando', 'taxiando'), ('pronto', 'pronto'), ('autorizado', 'autorizado'), ('em voo', 'em voo'), ('aterrissado', 'aterrissado')], max_length=20)),
                ('origem', models.CharField(max_length=100)),
                ('destino', models.CharField(max_length=100)),
                ('partida_prevista', models.DateTimeField()),
                ('chegada_prevista', models.DateTimeField()),
                ('partida_real', models.DateTimeField()),
                ('chegada_real', models.DateTimeField()),
            ],
            options={
                'db_table': 'voos',
            },
        ),
    ]