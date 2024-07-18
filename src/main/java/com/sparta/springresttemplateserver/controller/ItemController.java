package com.sparta.springresttemplateserver.controller;

import com.sparta.springresttemplateserver.dto.ItemResponseDto;
import com.sparta.springresttemplateserver.dto.UserRequestDto;
import com.sparta.springresttemplateserver.entity.Item;
import com.sparta.springresttemplateserver.service.ItemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/server")
public class ItemController {

  private final ItemService itemService;

  public ItemController(ItemService itemService) {
    this.itemService = itemService;
  }

  // 클라이언트에서 보낸 URI객체의 .path(): .path("/api/server/get-call-obj")
  @GetMapping("/get-call-obj")
  public Item getCallObject(
          @RequestParam
          String query) {
    return itemService.getCallObject(query);
  }

  @GetMapping("/get-call-list")
  public ItemResponseDto getCallList() {
    return itemService.getCallList();
  }

  // @PathVariable
  @PostMapping("/post-call/{query}")
  public Item postCall(
          @PathVariable
          String query,
          @RequestBody
          UserRequestDto requestDto) {
    return itemService.postCall(query, requestDto);
  }

  @PostMapping("/exchange-call")
  public ItemResponseDto exchangeCall(
          @RequestHeader("X-Authorization")
          String token, // X-Authorization의 value값
          @RequestBody
          UserRequestDto requestDto) {
    return itemService.exchangeCall(token, requestDto);
  }
}