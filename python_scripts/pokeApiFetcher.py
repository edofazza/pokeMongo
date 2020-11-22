import requests
import json



# 1° generation
new_json = []

for i in range(1, 152):
    response = requests.get(f"https://pokeapi.co/api/v2/pokemon/{i}/")
    work_string_json = response.json()
    
    curr_json = {
        "id": work_string_json['id'],
        "name": work_string_json['name'],
        "weight": work_string_json['weight'],
        "height": work_string_json['height'],
        "types": [],
        "portrait": work_string_json['sprites']['other']['official-artwork']['front_default'],
        "sprite": work_string_json['sprites']['front_default']
    }

    for i in work_string_json['types']:
        curr_json["types"].append(i['type']['name'])

    new_json.append(curr_json)
    print(new_json)






with open('pokemon.json', 'w', encoding='utf-8') as f:
    json.dump(new_json, f, ensure_ascii=False, indent=4)
