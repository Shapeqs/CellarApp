import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BottleFormComponent } from './bottle-form.component';

describe('BottleFormComponent', () => {
  let component: BottleFormComponent;
  let fixture: ComponentFixture<BottleFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ BottleFormComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(BottleFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
