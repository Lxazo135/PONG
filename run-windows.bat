javac -cp slick/lib/slick.jar src/pong/*.java

java -Djava.library.path=lwjgl-2.9.3/lwjgl-2.9.3/native/windows/ -cp "src;slick/lib/slick.jar;slick/lib/lwjgl.jar" pong.Pong