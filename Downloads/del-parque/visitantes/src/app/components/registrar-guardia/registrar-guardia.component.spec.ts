import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RegistrarGuardiaComponent } from './registrar-guardia.component';

describe('RegistrarGuardiaComponent', () => {
  let component: RegistrarGuardiaComponent;
  let fixture: ComponentFixture<RegistrarGuardiaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RegistrarGuardiaComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(RegistrarGuardiaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
