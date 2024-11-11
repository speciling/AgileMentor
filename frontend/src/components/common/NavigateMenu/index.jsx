import React from 'react';
import styled from 'styled-components';
import { FaRunning, FaThList, FaBook, FaChartLine } from 'react-icons/fa';

const NavigateMenu = () => (
  <Container>
    <Title>이용하기</Title>
    <MenuItem>
      <Icon as={FaRunning} />
      <MenuText>백로그 및 스프린트</MenuText>
    </MenuItem>
    <MenuItem>
      <Icon as={FaThList} />
      <MenuText>칸반보드</MenuText>
    </MenuItem>
    <MenuItem>
      <Icon as={FaBook} />
      <MenuText>애자일 학습하기</MenuText>
    </MenuItem>
    <MenuItem>
      <Icon as={FaChartLine} />
      <MenuText>번다운 차트</MenuText>
    </MenuItem>
  </Container>
);

export default NavigateMenu;

const Container = styled.div`
  width: 100%;
  padding: 0 20px 20px 20px;
`;

const Title = styled.h3`
  display: flex;
  align-items: center;
  align-self: flex-start;
  font-size: 24px;
  font-weight: bold;
  margin-bottom: 30px;
  color: #151313;

  @media (max-width: 768px) {
    font-size: 22px;
    margin-bottom: 24px;
  }

  @media (max-width: 480px) {
    font-size: 20px;
    margin-bottom: 20px;
  }
`;

const MenuItem = styled.div`
  display: flex;
  align-items: center;
  margin-bottom: 20px;
  color: #666;
  font-size: 18px;
  cursor: pointer;
  transition: color 0.3s;

  &:hover {
    color: #333;
  }

  @media (max-width: 768px) {
    font-size: 16px;
    margin-bottom: 16px;
  }

  @media (max-width: 480px) {
    font-size: 14px;
    margin-bottom: 12px;
  }
`;

const Icon = styled.div`
  font-size: 18px;
  margin-right: 8px;

  @media (max-width: 768px) {
    font-size: 14px;
  }

  @media (max-width: 480px) {
    font-size: 12px;
  }
`;

const MenuText = styled.span`
  margin-left: 8px;
`;
