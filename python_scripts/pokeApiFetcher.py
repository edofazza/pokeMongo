import requests
import json



# 1° generation
"""
for i in range(1, 151):
    response = requests.get(f"https://pokeapi.co/api/v2/pokemon/{i}/")
    work_string_json = response.json()
    print(response.content)
    print(work_string_json)
"""
response = requests.get(f"https://pokeapi.co/api/v2/pokemon/1/")
work_string_json = response.json()
print(work_string_json)
print(work_string_json['id'])
print(work_string_json['name'])
print(work_string_json['height'])
print(work_string_json['weight'])
print(work_string_json['types'])
print(work_string_json['sprites']['other']['official-artwork']['front_default'])
print(work_string_json['sprites']['front_default'])

new_json_

