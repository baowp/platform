package com.abbcc.websoa.siteSync;

import javax.jws.WebService;

@WebService
public abstract interface SyncUserSite
{
  public abstract String syncUserSite(String paramString1, String paramString2, String paramString3, String paramString4);

  public abstract String syncUserResource(String paramString1, String paramString2, String paramString3, String paramString4);

  public abstract String setXmlCon(String paramString1, String paramString2);
}

/* Location:           G:\software\abbcc_websoa.jar
 * Qualified Name:     com.abbcc.websoa.siteSync.SyncUserSite
 * JD-Core Version:    0.6.2
 */