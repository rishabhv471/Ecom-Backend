package com.ecom.ecom.controller;


public record AuthResponseDto(String token, AuthStatus authStatus) {
}