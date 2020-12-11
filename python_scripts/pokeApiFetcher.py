import requests
import json
# 1° generation
new_json = []
description = ""

for i in range(500, 894):
    response = requests.get(f"https://pokeapi.co/api/v2/pokemon/{i}/")
    work_string_json = response.json()
    response = requests.get(f"https://pokeapi.co/api/v2/pokemon-species/{i}/")
    work_string_json2 = response.json()

    for desc in work_string_json2['flavor_text_entries']:
        if(desc['language']['name'] == "en"):
            description = desc['flavor_text']
            break

    curr_json = {
        "id": work_string_json['id'],
        "name": work_string_json['name'],
        "weight": work_string_json['weight'],
        "height": work_string_json['height'],
        "capture_rates": [],
        "capture_rate": work_string_json2['capture_rate'],
        "biology": description,
        "types": [],
        "portrait": work_string_json['sprites']['other']['official-artwork']['front_default'],
        "sprite": work_string_json['sprites']['front_default']
    }

    print(i)
    for i in work_string_json['types']:
        curr_json["types"].append(i['type']['name'])

    new_json.append(curr_json)
    
with open('pokemon2.json', 'a', encoding='utf-8') as f:
    json.dump(new_json, f, ensure_ascii=False, indent=4)
