import React from 'react';
import styled from 'styled-components';
import { FiLogOut } from 'react-icons/fi';

const LogoutButton = () => (
  <Button>
    <LogoutIcon />
    프로젝트 나가기
  </Button>
);

export default LogoutButton;

const Button = styled.button`
  display: flex;
  align-items: center;
  background: none;
  border: none;
  color: #4a4a4a;
  font-size: 18px;
  cursor: pointer;
  padding: 10px;
  font-family: 'PaperlogyBold', sans-serif;

  &:hover {
    color: #333;
  }
`;

const LogoutIcon = styled(FiLogOut)`
  margin-right: 8px;
  font-size: 24px;
`;
