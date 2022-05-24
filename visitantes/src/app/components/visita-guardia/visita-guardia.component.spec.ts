import { ComponentFixture, TestBed } from '@angular/core/testing';

import { VisitaGuardiaComponent } from './visita-guardia.component';

describe('VisitaGuardiaComponent', () => {
  let component: VisitaGuardiaComponent;
  let fixture: ComponentFixture<VisitaGuardiaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ VisitaGuardiaComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(VisitaGuardiaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
