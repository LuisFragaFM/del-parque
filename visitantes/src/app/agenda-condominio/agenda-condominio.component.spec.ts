import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AgendaCondominioComponent } from './agenda-condominio.component';

describe('AgendaCondominioComponent', () => {
  let component: AgendaCondominioComponent;
  let fixture: ComponentFixture<AgendaCondominioComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AgendaCondominioComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AgendaCondominioComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
