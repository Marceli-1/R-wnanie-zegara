# Wymagany plik input.txt do poprawnego działania
# Pierwsza linia pliku zawiera: licznik; druga: mianownik
lines = []

try:
    file_in = open("input.txt", "r")
    lines = file_in.readlines()
    file_in.close()
except IOError:
    print("Nie znaleziono pliku")
    exit(-1)

if lines[0] == None:
    lines[0] = 12
if lines[1] == None:
    lines[1] = 11
 
try:
    x = int(lines[0])
    y = int(lines[1])
except ValueError:
    print("Nieprawidłowe dane wejściowe, program zostanie wykonany na poniższych danych:")
    print("licznik: 12, mianownik: 11")
    x = 12
    y = 11

var = res = float(x / y)
h = int(var)  # godziny
var = float((var - h) * 60)
m = int(var)  # minuty
var = float((var - m) * 60)
s = int(var)  # sekundy
var = float((var - s) * 100)
ms = int(var)

result3 = "{} h {} m {} s {} ms".format(h, m, s, ms)
result2 = ("Od 12:00 do kolejnej godziny, kiedy wskazowki sie pokryly mineło: {} h {} m {} s {} ms".format(h, m, s, ms))
print(result2)
result = float(res * 60 * 60)
print("Taki wynik odpowiada: ", result, "sekundom")

try:
    file_out = open("output.txt", "w")
    file_out.writelines(str(result))
    file_out.writelines("\n")
    file_out.writelines(str(result3))
    file_out.close()
except IOError:
    print("Błąd zapisu danych")
    exit(0)

exit(1)
