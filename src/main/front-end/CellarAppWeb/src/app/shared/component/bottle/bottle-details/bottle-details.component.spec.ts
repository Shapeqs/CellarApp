import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BottleDetailsComponent } from './bottle-details.component';

describe('BottleDetailsComponent', () => {
  let component: BottleDetailsComponent;
  let fixture: ComponentFixture<BottleDetailsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ BottleDetailsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(BottleDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
