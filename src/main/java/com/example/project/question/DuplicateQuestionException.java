package com.example.project.question;

import com.example.project.common.DuplicateResourceException;

public class DuplicateQuestionException extends DuplicateResourceException {
  public DuplicateQuestionException() {
    super("This Question is already in use");
  }
}
