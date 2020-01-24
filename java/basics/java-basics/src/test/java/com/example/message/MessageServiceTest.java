package com.example.message;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author DAI Yamasaki
 */
class MessageServiceTest {

    private MessageService messageService;

    @BeforeEach
    void setUp() {
        this.messageService = new MessageService();
    }

    @DisplayName("MessageService.get()")
    @Test
    public void get() {
        assertThat(this.messageService.get("World")).isNotNull();
    }

}