package com.example.Kallas.dto;

import com.example.Kallas.enums.Roles;

public record RegisterDto(String login, Roles role, String password) {
}
