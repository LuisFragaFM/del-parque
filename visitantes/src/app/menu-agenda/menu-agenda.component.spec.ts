import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MenuAgendaComponent } from './menu-agenda.component';

describe('MenuAgendaComponent', () => {
  let component: MenuAgendaComponent;
  let fixture: ComponentFixture<MenuAgendaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MenuAgendaComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(MenuAgendaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
