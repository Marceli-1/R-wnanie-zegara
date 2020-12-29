@echo off
:menu
cls
echo Projekt zaliczeniowy z j©zyk¢w skryptowych
echo ==============================
echo 1	Informacja na temat polecenia
echo 2	Wykonaj obliczenia (python)
echo 3	Utworz kopie zapasowa
echo 4	Zakoäcz
echo ==============================
set /p select=Wybierz opcje (np. 2):
IF %select%==1 GOTO opt1
IF %select%==2 GOTO opt2
IF %select%==3 goto opt3
IF %select%==4 goto exit
:opt1
echo ====================================================================
echo Polecenie jest nast©puj¥ce:
echo Wskaz¢wki zegara s¥ ustawione na godzin© 1200 . Nale¾y napisa† program
echo obliczaj¥cy czas w sekundach jaki upˆyn¥ˆ od momentu startu zegara do momentu
echo ustawienia si© wskaz¢wek w jednej linii. Wynik nale¾y zapisa† w pliku "output.txt".
echo ====================================================================
pause
goto menu
:opt2
echo ====================================================================
python solve.py
If %ERRORLEVEL% == 1 (echo Skrypt zadziaˆaˆ prawidˆowo)
If %ERRORLEVEL% == 0 (echo Bˆ¥d zapisu plik¢w wyj˜ciowych)
If %ERRORLEVEL% == -1 (echo Bˆ¥d otwarcia plik¢w wej˜ciowych)

python makeHTML.py
If %ERRORLEVEL% == 1 (echo Zapis raport w postaci pliku html zadziaˆaˆ prawidˆowo)
If %ERRORLEVEL% == 0 (echo Bˆ¥d zapisu plik¢w wyj˜ciowych w postaci pliku html)
If %ERRORLEVEL% == -1 (echo Bˆ¥d otwarcia plik¢w wej˜ciowych podczas tworzenia raportu)
echo ====================================================================

pause
goto menu
:opt3
echo ====================================================================
echo Kopiowanie folderu %cd%...
echo Usuwanie starej kopii zapasowej...
rmdir /S /Q %userprofile%\Backup
echo Tworzenie nowej kopii zapasowej...
mkdir %userprofile%\Backup
xcopy /e /v "%cd%" "%userprofile%\Backup"
echo ====================================================================
pause
goto menu
:exit
echo Koniec
pause