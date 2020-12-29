# Zapis do pliku html
try:
    file_in = open("input.txt", "r")
    lines = file_in.readlines()
    file_in.close()
except IOError:
    print("Nie znaleziono pliku")
    exit(-1)

try:
    x = int(lines[0])
    y = int(lines[1])
except ValueError:
    print("Nieprawidłowe dane wejściowe, program zostanie wykonany na poniższych danych:")
    print("licznik: 12, mianownik: 11")
    x = 12
    y = 11

try:
    file_in = open("output.txt", "r")
    lines = file_in.readlines()
    file_in.close()
except IOError:
    print("Nie znaleziono pliku")
    exit(-1)
    
try:
    message_res_2 = lines[0]
    message_res_4 = lines[1]
except ValueError:
    print("Nieprawidłowe dane wejściowe, program zostanie wykonany na poniższych danych:")
    print("message_res_2: 3927.272727272727, message_res_4: 1 h 5 m 27 s 27 ms")
    message_res_2 = lines[0]
    message_res_4 = lines[1]


try:
    file_html = open("output.html", "w")

    message = """
    <!DOCTYPE html>
    <html>
    <head>
    <link rel="stylesheet" href="styles.css">
    </head>
    <body>


    <table class="steelBlueCols">
    <thead>
    <tr>
    <th>Wejście</th>
    <th>Wartość wejścia</th>
    <th>Wyjście</th>
    <th>Wartość wyjścia</th>
    </tr>
    </thead>
    <tbody>
    <tr>
    <td>Licznik</td><td>"""

    message_res_1 = x
    message2 = """
    </td>
    <td>Sekundy</td><td>"""
    #message_res_2 = result
    message3 = """
    </td></tr>
    <tr>
    <td>Mianownik</td><td>"""
    message_res_3 = y
    message4 = """
    </td>
    <td>Czas</td><td>"""
    #message_res_4 = ("{} h {} m {} s {} ms".format(h, m, s, ms))
    message5 = """
    </td></tr>
    <tr>
    </tbody>
    </tr>
    </table>

    </body>
    </html>"""
    file_html.writelines(str(message))
    file_html.writelines(str(message_res_1))
    file_html.writelines(str(message2))
    file_html.writelines(str(message_res_2))
    file_html.writelines(str(message3))
    file_html.writelines(str(message_res_3))
    file_html.writelines(str(message4))
    file_html.writelines(str(message_res_4))
    file_html.writelines(str(message5))
    file_html.close()
except IOError:
    print("Błąd zapisu danych")
    exit(0)
    
exit(1)