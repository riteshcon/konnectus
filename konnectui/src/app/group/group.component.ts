import { Component, Input } from '@angular/core';

@Component({
    selector: 'custom-button',
  template: '<button (click)="handleClick()">{{label}}</button>'
  })
  export class GroupComponent {
    @Input() label = 'Group';
    handleClick() {
     console.log('clicked');
      }
  }