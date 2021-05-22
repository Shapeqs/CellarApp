import { TestBed } from '@angular/core/testing';

import { CastelService } from './castel.service';

describe('CastelService', () => {
  let service: CastelService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CastelService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
