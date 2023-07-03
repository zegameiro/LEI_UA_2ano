import requests

x = requests.post("http://127.0.0.1:5000/music", files={"file": open("test.mp3", "rb")})
print(x.text)
print(x.status_code)

x = str(x.json()["music_id"])
y = requests.post("http://127.0.0.1:5000/music/" + x)

# z = requests.post("http://127.0.0.1:5000/reset")