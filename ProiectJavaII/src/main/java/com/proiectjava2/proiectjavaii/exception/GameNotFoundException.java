package com.proiectjava2.proiectjavaii.exception;

public class GameNotFoundException extends  Exception{
        private String message;

        public GameNotFoundException(String message) {
            this.message = message;
        }
        public String getMessage(){
            return message;
        }
}
